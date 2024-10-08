import groovy.json.JsonSlurperClassic

def withCurl(String jsonParameters) {

    def apiParams = new groovy.json.JsonSlurperClassic().parseText(jsonParameters)

    def payload = """{
        "name": "${apiParams.name}",
        "description": "${apiParams.des}",
        "private": ${apiParams.private}
    }"""

    // Escape single quotes in payload for the shell
    // def escapedPayload = payload.replaceAll("'", "\\'")

    sh """
        echo escapedPayload
        curl -L \\
             -X POST \\
             -H "Accept: application/vnd.github+json" \\
             -H "Authorization: Bearer ${apiParams.token}" \\
             -H "X-GitHub-Api-Version: 2022-11-28" \\
             https://api.github.com/user/repos \\
             -d '${payload}'

    """
}

def withHTTP(String jsonParameters) {
    def apiParams = new groovy.json.JsonSlurperClassic().parseText(jsonParameters)

    def payload = """{
        "name": "${apiParams.name}",
        "description": "${apiParams.des}",
        "private": ${apiParams.private}
    }"""

    // Escape single quotes in payload for the shell
    // def escapedPayload = payload.replaceAll("'", "\\'")

    def response = httpRequest(
        acceptType: 'APPLICATION_JSON',
        contentType: 'APPLICATION_JSON',
        httpMode: 'POST',
        url: 'https://api.github.com/user/repos',
        requestBody: payload,
        customHeaders: [
            [name: 'Authorization', value: "Bearer ${apiParams.token}"],
            [name: 'X-GitHub-Api-Version', value: '2022-11-28']
        ]
    )

    echo "Response: ${response}"
}
