package com.containersol.test

import groovy.util.logging.Slf4j

@Slf4j
class Agent extends DslLoadable {

    def imageName     = "containersol/mesos-agent"
    def imageTag      = "0.25"
    def loggingLevel  = "INFO"

    def agent(@DelegatesTo(Agent) Closure cl) {
        def agent = new Agent()
        delegateTo(agent, cl)
        agents.add(agent)
    }

}
