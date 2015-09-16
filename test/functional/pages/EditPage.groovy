package pages

class EditPage extends ScaffoldPage {

    static at = {
        heading.text() ==~ /.+を編集/
    }

    static content = {
        updateButton(to: ShowPage) { $("input", value: "更新") }
        deleteButton(to: ListPage) { $("input", value: "削除") }
    }

}
