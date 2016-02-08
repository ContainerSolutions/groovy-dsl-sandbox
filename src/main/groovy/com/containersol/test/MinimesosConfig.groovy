package com.containersol.test

import groovy.util.logging.Slf4j

@Slf4j
class MinimesosConfig {

    static void main(String[] args) {

        File file = new File("minimesos.cfg")

        Binding binding = new Binding();
        MinimesosDsl jenkinsDsl = new MinimesosDsl()
        binding.setVariable("minimesos", jenkinsDsl)
        GroovyShell shell = new GroovyShell(binding)
        Script script = shell.parse(file)
        script.run()

        log.info "DONE"

    }

}