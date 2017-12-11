/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain

import grails.gorm.annotation.Entity
import groovy.transform.ToString

@Entity
@ToString(includeNames=true)
class Thought {
    long uuid
    int rating
    String comment
    Soup soup
    
    
    static constraints = {
        rating nullable: true
        comment nullable: true
        uuid unique:['soup']
    }
	
}

