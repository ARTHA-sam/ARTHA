#!/usr/bin/env node

const { Command } = require('commander');
const chalk = require('chalk');
const packageJson = require('../package.json');

const program = new Command();

program
    .name('artha')
    .description('FastAPI for Java - Simple backend framework')
    .version(packageJson.version);

program
    .command('dev')
    .description('Start development server with hot reload')
    .option('-p, --port <port>', 'Port to run on', '8080')
    .action((options) => {
        require('../src/commands/dev')(options);
    });

program
    .command('new <project-name>')
    .description('Create a new ARTHA project')
    .option('-t, --template <name>', 'Template to use', 'minimal')
    .action((projectName, options) => {
        require('../src/commands/new')(projectName, options);
    });

program
    .command('build')
    .description('Build production JAR')
    .action(() => {
        require('../src/commands/build')();
    });
// after other requires
program
    .command('add <package>')
    .description('Add a dependency to your project')
    .option('-v, --version <version>', 'Specify version')
    .action((packageName, options) => {
        require('../src/commands/add')(packageName, options.version);
    });

// Show help if no command
if (process.argv.length === 2) {
    program.help();
}

program.parse(process.argv);
