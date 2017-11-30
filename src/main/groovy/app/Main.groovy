/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app

import org.grails.orm.hibernate.HibernateDatastore

import domain.*
import grails.gorm.transactions.Transactional

def configuration = [
    'hibernate.hbm2ddl.auto':'update',
    'hibernate.dialect': 'org.hibernate.dialect.Comdb2Dialect',
    'dataSource.url': 'jdbc:comdb2://localhost/testdb',
    'dataSource.driverClassName': 'com.bloomberg.comdb2.jdbc.Driver',
    'dataSource.logSql': 'true'
]


HibernateDatastore datastore = new HibernateDatastore(configuration, Person)

@Transactional
def doSomething() {
    def p = new Person("Apple", "Cherry")
    println p
    println p.save(flush:true)
    
    
    println Person.findByFirstName("Apple")
        
        
    println "done"
}

datastore.withNewSession {doSomething()}



