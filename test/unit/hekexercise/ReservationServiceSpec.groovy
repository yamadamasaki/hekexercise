package hekexercise

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.spockframework.mock.ZeroOrNullResponse
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ReservationService)
@Mock([Person, Appliance, Reservation])
class ReservationServiceSpec extends Specification {

    def reservationService = new ReservationService()

    def who
    def what
    def from
    def to

    def gc
    def p3

    def setup() {
        who = new Person(name:"山田").save()
        what = new Appliance(name:"プロジェクタ1").save()
        from = new Date(115/*2015*/, 7/*august*/, 1, 9, 0)
        to = new Date(115/*2015*/, 7/*august*/, 1, 12, 0)

        gc = new Person(name:"Graham Chapman").save()
        p3 = new Appliance(name:"プロジェクタ3").save()
    }

    def cleanup() {
    }

    void "予約を一つだけ作る"() {
        when:
        def r = reservationService.reserve(who, what, from, to)
        then:
        r.who == who
        r.what == what
        r.fromDate == from
        r.toDate == to
    }

    def personService = Stub(PersonService)
    def applianceService = Stub(ApplianceService)

    void "予約を一つだけ作る2"() {
        given:
        personService.add(_) >> new Person(name:"yamada").save()
        applianceService.add(_) >> new Appliance(name:"projector").save()
        who = personService.add([name:"yamada"])
        what = applianceService.add([name:"projector"])
        when:
        def r = reservationService.reserve(who, what, from, to)
        then:
        r.who == who
        r.what == what
        r.fromDate == from
        r.toDate == to
    }

    void "指定された機器が予約されているかどうかを確認する"() {
        when:
        reservationService.reserve(who, what, from, to)
        then:
        !reservationService.isReserved(what, new Date(115, 7, 1, 8, 0))
        reservationService.isReserved(what, new Date(115, 7, 1, 9, 0))
        reservationService.isReserved(what, new Date(115, 7, 1, 10, 0))
        reservationService.isReserved(what, new Date(115, 7, 1, 11, 0))
        !reservationService.isReserved(what, new Date(115, 7, 1, 12, 0))
        !reservationService.isReserved(what, new Date(115, 7, 1, 13, 0))
    }

    void "指定された機器が予約されているかどうかを確認する (data-driven)"() {
        when:
        reservationService.reserve(who, what, from, to)
        then:
        reservationService.isReserved(what, new Date(115, 7, 1, hour, 0)) == result
        where:
        hour || result
        8    || false
        9    || true
        10   || true
        11   || true
        12   || false
        13   || false
    }

    void testIsBooked() {
        when:
        reservationService.reserve(who, what, from, to)
        then:
        reservationService.isBooked(new Reservation(who:who, what:what, fromDate:new Date(115, 7, 1, f, 0), toDate:new Date(115, 7, 1, t, 0))) == result
        where:
        f | t || result
        7 | 8 || false
        7 | 9 || false
        7 | 11 || true
        7 | 12 || true
        7 | 13 || true
        9 | 10 || true
        9 | 12 || true
        9 | 13 || true
        10 | 11 || true
        10 | 12 || true
        10 | 13 || true
        12 | 13 || false
    }

    void "同じ機器に対して重複する予約をする"() {
        when:
        reservationService.reserve(who, what, from, to)
        then:
        !reservationService.reserve(who, what, from, new Date(115, 7, 1, 10, 0))
    }

    void "同じ機器に対して重複しない予約をする"() {
        when:
        reservationService.reserve(who, what, from, to)
        then:
        reservationService.reserve(who, what, new Date(115, 7, 1, 12, 0), new Date(115, 7, 1, 15, 0))
    }

    void "異なる機器に対して重複する予約をする"() {
        when:
        reservationService.reserve(who, what, from, to)
        then:
        reservationService.reserve(who, new Appliance(name:"プロジェクタ2").save(), from, new Date(115, 7, 1, 10, 0))
    }

    void "別のひとが同じ機器に対して重複しない予約をする"() {
        when:
        reservationService.reserve(who, what, from, to)
        then:
        reservationService.reserve(gc, what, new Date(115, 7, 1, 12, 0), new Date(115, 7, 1, 15, 0))
    }

    void "別のひとが同じ機器に対して重複する予約をする"() {
        when:
        reservationService.reserve(who, what, from, to)
        then:
        !reservationService.reserve(gc, what, new Date(115, 7, 1, 10, 0), new Date(115, 7, 1, 11, 0))
    }

    void "別のひとが異なる機器に対して重複しない予約をする"() {
        when:
        reservationService.reserve(who, what, from, to)
        then:
        reservationService.reserve(gc, p3, new Date(115, 7, 1, 12, 0), new Date(115, 7, 1, 15, 0))
    }

    void "別のひとが異なる機器に対して重複する予約をする"(){
        when:
        reservationService.reserve(who, what, from, to)
        then:
        reservationService.reserve(gc, p3, new Date(115, 7, 1, 10, 0), new Date(115, 7, 1, 11, 0))
    }

}
