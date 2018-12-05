def call(String id) {
    configFileProvider([configFile(fileId: 'trello', variable: 'configfile')]) {
        def config = readJSON(text: readFile(file: configfile))
        String data = """{"idValue": "${config['results'][currentBuild.currentResult]}"}"""
        def response = httpRequest(contentType: 'APPLICATION_JSON',
                                   httpMode: 'PUT',
                                   url: "https://api.trello.com/1/card/${id}/customField/${config['field']}/item?key=${config['key']}&token=${config['token']}",
                                   requestBody: data
        )
    }
}
