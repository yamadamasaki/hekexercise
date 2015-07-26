package hekexercise

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class StubexperimentSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        when:
        def s = Stub(Foo)
        s.bar(_) >> "たなばた"
        then:
        s.bar(1) == "たなばた"
    }

    void "test something2"() {
        when:
        def s = Stub(Foo)
        s.bar(_) >>> ["1", "2"]
        then:
        s.bar(1) == "1"
        s.bar(1) == "2"
        s.bar(1) == "2"
    }
}
