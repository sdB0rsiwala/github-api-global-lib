import groovy.json.JsonSlurper

def withCurl(String jsonParameters) {

    sh """
        def apiParams = new groovy.json.JsonSlurper().parseText(jsonParameters)

        def payload = """{
            "name": "${apiParams.name}",
            "description": "${apiParams.des}",
            "private": ${apiParams.private}
        }"""
        curl -L \\
             -X POST \\
             -H "Accept: application/vnd.github+json" \\
             -H "Authorization: Bearer ${apiParams.token}" \\
             -H "X-GitHub-Api-Version: 2022-11-28" \\
             https://api.github.com/user/repos \\
             -d '${payload}'

    """
}