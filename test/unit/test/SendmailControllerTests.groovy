package test



import org.junit.*
import grails.test.mixin.*

@TestFor(SendmailController)
@Mock(Sendmail)
class SendmailControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/sendmail/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.sendmailInstanceList.size() == 0
        assert model.sendmailInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.sendmailInstance != null
    }

    void testSave() {
        controller.save()

        assert model.sendmailInstance != null
        assert view == '/sendmail/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/sendmail/show/1'
        assert controller.flash.message != null
        assert Sendmail.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/sendmail/list'


        populateValidParams(params)
        def sendmail = new Sendmail(params)

        assert sendmail.save() != null

        params.id = sendmail.id

        def model = controller.show()

        assert model.sendmailInstance == sendmail
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/sendmail/list'


        populateValidParams(params)
        def sendmail = new Sendmail(params)

        assert sendmail.save() != null

        params.id = sendmail.id

        def model = controller.edit()

        assert model.sendmailInstance == sendmail
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/sendmail/list'

        response.reset()


        populateValidParams(params)
        def sendmail = new Sendmail(params)

        assert sendmail.save() != null

        // test invalid parameters in update
        params.id = sendmail.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/sendmail/edit"
        assert model.sendmailInstance != null

        sendmail.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/sendmail/show/$sendmail.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        sendmail.clearErrors()

        populateValidParams(params)
        params.id = sendmail.id
        params.version = -1
        controller.update()

        assert view == "/sendmail/edit"
        assert model.sendmailInstance != null
        assert model.sendmailInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/sendmail/list'

        response.reset()

        populateValidParams(params)
        def sendmail = new Sendmail(params)

        assert sendmail.save() != null
        assert Sendmail.count() == 1

        params.id = sendmail.id

        controller.delete()

        assert Sendmail.count() == 0
        assert Sendmail.get(sendmail.id) == null
        assert response.redirectedUrl == '/sendmail/list'
    }
}
