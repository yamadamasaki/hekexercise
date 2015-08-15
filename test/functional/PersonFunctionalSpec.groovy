import geb.spock.GebReportingSpec
import org.openqa.selenium.Dimension
import spock.lang.*

import pages.*

@Stepwise
class PersonFunctionalSpec extends GebReportingSpec {
    def "there are no people"() {
        when:
        to ListPage
        then:
        personRows.size() == 0
    }

    def "add a person"() {
        when:
        newPersonButton.click()
        then:
        at CreatePage
    }

    def "enter the details"() {
        when:
        name = "Luke"
        createButton.click()
        then:
        at ShowPage
    }

    def "check the entered details"() {
        expect:
        name == "Luke"
    }

    def "edit the details"() {
        when:
        editButton.click()
        then:
        at EditPage
        when:
        updateButton.click()
        then:
        at ShowPage
    }

    def "check in listing"() {
        when:
        to ListPage
        then:
        personRows.size() == 1
        def row = personRow(0)
        row.name == "Luke"
    }

    def "show person"() {
        when:
        personRow(0).showLink.click()
        then:
        at ShowPage
    }

    def "delete user"() {
        given:
        def deletedId = id
        when:
        withConfirm { deleteButton.click() }
        then:
        at ListPage
        message == "Person $deletedId deleted"
        personRows.size() == 0
    }
}
