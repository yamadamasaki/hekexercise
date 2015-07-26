package hekexercise

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Reservation)
@Mock([Person, Appliance])
class ReservationSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "インスタンスを生成する"() {
        setup:
        def who = new Person(name:"山田").save()
        def what = new Appliance(name:"プロジェクタ1").save()
        def from = new Date(115/*2015*/, 7/*august*/, 1, 10, 0)
        def to = new Date(115/*2015*/, 7/*august*/, 1, 12, 0)
        new Reservation(who:who, what:what, fromDate:from, toDate:to).save()
        when:
        def r = Reservation.first()
        // Mockでは Reservation.findByWho(Person) は無理みたい
        // grails.test.mixin.hibernate.HibernateTestMixin なら可能かもしれない
        then:
        r.who == who
        r.what == what
        r.fromDate == from
        r.toDate == to
    }
}
