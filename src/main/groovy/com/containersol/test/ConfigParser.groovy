package com.containersol.test

import groovy.util.logging.Slf4j

@Slf4j
class ConfigParser {

    static void main(String[] args) {
        File file = new File("minimesos.cfg")
        ConfigParser parser = new ConfigParser()
        parser.parse(file);
    }

    Config parse(String config) {
        Binding binding = new Binding();

        Config minimesosDsl = new Config()
        binding.setVariable("minimesos", minimesosDsl)

        GroovyShell shell = new GroovyShell(binding)
        Script script = shell.parse(config)
        script.run()

        return minimesosDsl
    }

    Config parse(File file) {
        return parse(file.text)
    }

}