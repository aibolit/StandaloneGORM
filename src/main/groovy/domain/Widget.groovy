package domain

import grails.gorm.annotation.Entity
import groovy.transform.ToString 

@Entity
@ToString(includeNames=true)
class Widget {

    String type
    Date lastModified

    static constraints = {
    }


}
