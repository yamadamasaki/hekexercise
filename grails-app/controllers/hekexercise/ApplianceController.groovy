package hekexercise



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ApplianceController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Appliance.list(params), model:[applianceInstanceCount: Appliance.count()]
    }

    def show(Appliance applianceInstance) {
        respond applianceInstance
    }

    def create() {
        respond new Appliance(params)
    }

    @Transactional
    def save(Appliance applianceInstance) {
        if (applianceInstance == null) {
            notFound()
            return
        }

        if (applianceInstance.hasErrors()) {
            respond applianceInstance.errors, view:'create'
            return
        }

        applianceInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'appliance.label', default: 'Appliance'), applianceInstance.id])
                redirect applianceInstance
            }
            '*' { respond applianceInstance, [status: CREATED] }
        }
    }

    def edit(Appliance applianceInstance) {
        respond applianceInstance
    }

    @Transactional
    def update(Appliance applianceInstance) {
        if (applianceInstance == null) {
            notFound()
            return
        }

        if (applianceInstance.hasErrors()) {
            respond applianceInstance.errors, view:'edit'
            return
        }

        applianceInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Appliance.label', default: 'Appliance'), applianceInstance.id])
                redirect applianceInstance
            }
            '*'{ respond applianceInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Appliance applianceInstance) {

        if (applianceInstance == null) {
            notFound()
            return
        }

        applianceInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Appliance.label', default: 'Appliance'), applianceInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'appliance.label', default: 'Appliance'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
