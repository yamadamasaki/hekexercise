package hekexercise

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ReservationService)
@Mock([Person, Appliance, Reservation])
class ReservationServiceSpec extends Specification {

    def reservationService = new ReservationService()

    def setup() {
    }

    def cleanup() {
    }

    void "予約を一つだけ作る"() {
        setup:
        def who = new Person(name:"山田").save()
        def what = new Appliance(name:"プロジェクタ1").save()
        def from = new Date(115/*2015*/, 7/*august*/, 1, 10, 0)
        def to = new Date(115/*2015*/, 7/*august*/, 1, 12, 0)
        when:
        def r = reservationService.reserve(who, what, from, to)
        then:
        r.who == who
        r.what == what
        r.fromDate == from
        r.toDate == to
    }
}
