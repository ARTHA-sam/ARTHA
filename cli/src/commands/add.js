const fs = require('fs-extra');
const chalk = require('chalk');
const DependencyManager = require('../utils/dependency-manager');

async function addCommand(packageName, version) {
    console.log(chalk.cyan(`\n📦 Adding ${packageName}...\n`));
    if (!await fs.pathExists('artha.json')) {
        console.log(chalk.red('❌ No artha.json found!'));
        process.exit(1);
    }
    const config = await fs.readJson('artha.json');
    if (!config.dependencies) config.dependencies = {};
    if (!version) version = await getLatestVersion(packageName);
    config.dependencies[packageName] = version;
    await fs.writeJson('artha.json', config, { spaces: 2 });
    console.log(chalk.green(`✅ Added ${packageName}@${version} to artha.json`));
    const depManager = new DependencyManager(process.cwd());
    await depManager.install();
    console.log(chalk.cyan('\n💡 Restart your server:\n  artha dev\n'));
}

async function getLatestVersion(packageName) {
    const LATEST = {
        'postgresql': '42.7.1',
        'mysql': '8.2.0',
        'redis': '4.3.1',
        'lombok': '1.18.30',
        'gson': '2.10.1',
        'jwt': '4.4.0',
        'mongodb': '4.11.1',
        'sqlite': '3.44.1.0'
    };
    return LATEST[packageName] || '1.0.0';
}

module.exports = addCommand;
