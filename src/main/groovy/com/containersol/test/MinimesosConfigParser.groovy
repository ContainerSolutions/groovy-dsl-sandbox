package com.containersol.test

import groovy.util.logging.Slf4j

@Slf4j
class MinimesosConfigParser {

    static void main(String[] args) {
        File file = new File("minimesos.cfg")
        MinimesosConfigParser config = new MinimesosConfigParser()
        config.parse(file);
    }

    MinimesosDsl parse(String config) {
        Binding binding = new Binding();
        MinimesosDsl minimesosDsl = new MinimesosDsl()
        binding.setVariable("minimesos", minimesosDsl)
        GroovyShell shell = new GroovyShell(binding)
        Script script = shell.parse(config)
        script.run()

        log.info "DONE"

        return minimesosDsl
    }

    MinimesosDsl parse(File file) {
        Binding binding = new Binding();
        MinimesosDsl minimesosDsl = new MinimesosDsl()
        binding.setVariable("minimesos", minimesosDsl)
        GroovyShell shell = new GroovyShell(binding)
        Script script = shell.parse(file)
        script.run()

        log.info "DONE"

        return minimesosDsl
    }

}