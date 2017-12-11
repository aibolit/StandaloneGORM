package domain

import grails.gorm.annotation.Entity
import groovy.transform.ToString

@Entity
@ToString(includeNames=true)
class User {

    String name 
    int myNum = 4
    List<Widget> widgets = []
    String title


    static constraints = {
        name maxSize: 64
        title nullable: true
    }

}
