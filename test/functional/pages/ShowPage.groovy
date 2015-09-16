package pages

class ShowPage extends ScaffoldPage {

    static at = {
        heading.text() ==~ /Person詳細/
    }

    static content = {
        editButton(to: EditPage) { $("a", text: "編集") }
        deleteButton(to: ListPage) { $("input", value: "削除") }
        row { $("li.fieldcontain span.property-label", text: it).parent() }
        value { row(it).find("span.property-value").text() }
        name { value("Name") }
    }
}
