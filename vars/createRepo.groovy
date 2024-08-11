import groovy.json.JsonSlurper

def withCurl(String jsonParameters) {

    def jsonSlurper = new JsonSlurper()
    def apiParams = jsonSlurper.parseText(jsonParameters)

    def payload = """{
        "name": "${apiParams.name}",
        "description": "${apiParams.des}",
        "private": ${apiParams.private}
    }"""

    // Escape single quotes in payload for the shell
    def escapedPayload = payload.replaceAll("'", "\\'")

    sh """
        echo escapedPayload
        curl -L \\
             -X POST \\
             -H "Accept: application/vnd.github+json" \\
             -H "Authorization: Bearer %apiParams.token%" \\
             -H "X-GitHub-Api-Version: 2022-11-28" \\
             https://api.github.com/user/repos \\
             -d '${escapedPayload}'

    """
}