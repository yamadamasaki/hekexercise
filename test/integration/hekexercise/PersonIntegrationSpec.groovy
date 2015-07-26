package hekexercise

import grails.test.spock.IntegrationSpec

class PersonIntegrationSpec extends IntegrationSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        when:
        new Person(name:"山田").save(flush: true)
        then:
        Person.findAllByName("山田").size() == 1
        Person.findByName("山田").name == "山田"
    }
}
