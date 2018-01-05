/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app

import org.grails.orm.hibernate.HibernateDatastore
//org.springframework.transaction.annotation
import org.springframework.transaction.annotation.Isolation

import domain.*
import grails.gorm.transactions.Transactional


def configuration = [
    'hibernate.hbm2ddl.auto':'create-drop',
    'hibernate.dialect': 'org.hibernate.dialect.Comdb2Dialect',
    'dataSource.url': 'jdbc:comdb2://localhost/testdb',
    'dataSource.driverClassName': 'com.bloomberg.comdb2.jdbc.Driver',
    'dataSource.logSql': 'true',
    //'dataSource.formatSql': 'true'
]


def entities = [Widget, User, Soup, Thought]
HibernateDatastore datastore = new HibernateDatastore(configuration, *entities)


@Transactional(isolation=Isolation.READ_COMMITTED)
def doSomething() {
    def w = new Widget(type: "pqr")
    w.lastModified = new Date()
    println w
    //println w.save()

    def a = new User(name: "abc", title:"abct", widgets:[w])
    println a.save(flush:true)

    a.name = "def"
    println a.save()


    def b = new User(name: 'ijk')
    b.title = "title"
    b.save(flush:true)
    println b

    def c = new User(name: 'mno', title: "king")
    println "> for delete ${c.save(flush: true)}"
    

    println "> test deletion"
    println "> byId:${User.get(c.id)}, byName:${User.findByTitle('king')}"
    println "> delete ${User.findByName('mno').delete(flush:true)}"
    println "> find again ${User.get(c.id)}"
    
    println "done"
    
    
    Soup soup = new Soup(brand: 'a', product: 'b', flavor: 'c', live:true)
    println "> Saving soup: ${soup.save(flush:true)}"
    
    Thought th1 = new Thought(uuid: 7, rating: 2, soup: soup)
    println "> Saving thought 1: ${th1.save(flush:true)}"
    
    Thought th2 = new Thought(uuid: 7, rating: 4, soup: soup)
    println "> Saving thought 2: ${th2.save(flush:true)}"
    
    
    println "> Soup thoughts A: ${Thought.findBySoup(soup)}"
    
    println "> Soup thoughts B: ${Soup.get(soup.id).thoughts}"
}

datastore.withNewSession {doSomething()}



