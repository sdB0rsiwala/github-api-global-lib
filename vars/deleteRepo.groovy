import groovy.json.JsonSlurperClassic

def withCurl(String jsonParameters) {

    def apiParams = new groovy.json.JsonSlurperClassic().parseText(jsonParameters)

    // Escape single quotes in payload for the shell
    // def escapedPayload = payload.replaceAll("'", "\\'")

    sh """
        echo escapedPayload
        curl -L \\
             -X DELETE \\
             -H "Accept: application/vnd.github+json" \\
             -H "Authorization: Bearer ${apiParams.token}" \\
             -H "X-GitHub-Api-Version: 2022-11-28" \\
             https://api.github.com/repos/sdB0rsiwala/${apiParams.name} \\

    """
}
