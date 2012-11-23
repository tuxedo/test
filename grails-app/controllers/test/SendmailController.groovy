package test

import org.springframework.dao.DataIntegrityViolationException

class SendmailController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [sendmailInstanceList: Sendmail.list(params), sendmailInstanceTotal: Sendmail.count()]
    }

    def create() {
        [sendmailInstance: new Sendmail(params)]
    }

    /*def save() {
        def sendmailInstance = new Sendmail(params)
        if (!sendmailInstance.save(flush: true)) {
            render(view: "create", model: [sendmailInstance: sendmailInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'sendmail.label', default: 'Sendmail'), sendmailInstance.id])
        redirect(action: "show", id: sendmailInstance.id)
    }*/
	def save = {
		def sendmailInstance = new Sendmail(params)
		if (!sendmailInstance.hasErrors() && sendmailInstance.save()) {
			sendMail {
				to sendmailInstance.emailAdd
				subject sendmailInstance.title
				body sendmailInstance.content
			}
			flash.message = "SendMail ${sendmailInstance.id} created"
			redirect(action: "show", id: sendmailInstance.id)
		}
		else {
			render(view: 'create', model: [sendmailInstance : sendmailInstance])
		}
	}
    def show() {
        def sendmailInstance = Sendmail.get(params.id)
        if (!sendmailInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'sendmail.label', default: 'Sendmail'), params.id])
            redirect(action: "list")
            return
        }

        [sendmailInstance: sendmailInstance]
    }

    def edit() {
        def sendmailInstance = Sendmail.get(params.id)
        if (!sendmailInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sendmail.label', default: 'Sendmail'), params.id])
            redirect(action: "list")
            return
        }

        [sendmailInstance: sendmailInstance]
    }

    def update() {
        def sendmailInstance = Sendmail.get(params.id)
        if (!sendmailInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sendmail.label', default: 'Sendmail'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (sendmailInstance.version > version) {
                sendmailInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'sendmail.label', default: 'Sendmail')] as Object[],
                          "Another user has updated this Sendmail while you were editing")
                render(view: "edit", model: [sendmailInstance: sendmailInstance])
                return
            }
        }

        sendmailInstance.properties = params

        if (!sendmailInstance.save(flush: true)) {
            render(view: "edit", model: [sendmailInstance: sendmailInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'sendmail.label', default: 'Sendmail'), sendmailInstance.id])
        redirect(action: "show", id: sendmailInstance.id)
    }

    def delete() {
        def sendmailInstance = Sendmail.get(params.id)
        if (!sendmailInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'sendmail.label', default: 'Sendmail'), params.id])
            redirect(action: "list")
            return
        }

        try {
            sendmailInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'sendmail.label', default: 'Sendmail'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'sendmail.label', default: 'Sendmail'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
