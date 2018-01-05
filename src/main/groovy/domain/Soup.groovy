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

/**
 *
 * @author Aleks
 */
class Soup {
    
    String brand
    String product
    String flavor
    boolean live
    
    static hasMany = [thoughts: Thought]
    static mappedBy = [thoughts: 'soup']
    
	
}

