package pages

import geb.Module

class ListPage extends ScaffoldPage {
    static url = "person/index"

    static at = {
        title ==~ /Personリスト/
    }

    static content = {
        newPersonButton(to: CreatePage) { $("a", text: "Personを新規作成") }
        peopleTable { $("div.content table", 0) }
        personRow { module PersonRow, personRows[it] }
        personRows(required: false) { peopleTable.find("tbody").find("tr") }
    }
}

class PersonRow extends Module {
    static content = {
        cell { $("td", it) }
        cellText { cell(it).text() }
        cellHrefText{ cell(it).find('a').text() }
        name { cellText(0) }
        showLink(to: ShowPage) { cell(0).find("a") }
    }
}
