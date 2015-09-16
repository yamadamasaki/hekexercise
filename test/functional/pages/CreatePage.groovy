package pages

class CreatePage extends ScaffoldPage {

    static at = {
        title ==~ /.+を作成/
    }

    static content = {
        createButton(to: ShowPage) { create() }
    }

}
