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


def entities = [Person, User, Widget]
HibernateDatastore datastore = new HibernateDatastore(configuration, *entities)


@Transactional(isolation=Isolation.READ_COMMITTED)
def doSomething() {
    def p = new Person("Apple", "Cherry")
    println p
    println p.save(flush:true)
    
    
    println Person.findByFirstName("Apple")
        
        
    def w = new Widget(type: "pqr")
    w.lastModified = new Date()
    println w
    //println w.save()
    
    def a = new User(name: "abc", title:"abct", widgets:[w])
    println a.save(flush:true)

    a.name = "def"
    println a.save()


    def b = new User(name: 'ijk')
    b.title = "boss"
    b.save(flush:true)
    println b

    def c = new User(name: 'mno', title: "king")
    println "> for delete ${c.save(flush: true)}"
    

    println "> test deletion"
    println "> byId:${User.get(c.id)}, byName:${User.findByTitle('king')}"
    println "> delete ${User.findByName('mno').delete(flush:true)}"
    println "> find again ${User.get(c.id)}"
    
    println "done"
}

datastore.withNewSession {doSomething()}



