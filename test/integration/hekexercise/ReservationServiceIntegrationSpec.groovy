package hekexercise

import grails.test.spock.IntegrationSpec

class ReservationServiceIntegrationSpec extends IntegrationSpec {
    ReservationService reservationService

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
