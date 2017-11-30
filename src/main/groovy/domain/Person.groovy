/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain

import grails.gorm.annotation.Entity
import groovy.transform.ToString
import groovy.transform.TupleConstructor
import org.grails.datastore.gorm.GormEntity 

/**
 *
 * @author Aleks
 */
@Entity
@TupleConstructor
@ToString
class Person implements GormEntity<Person> { 
    String firstName
    String lastName
    static constraints = {
        firstName blank:false
        lastName blank:false
    }
}