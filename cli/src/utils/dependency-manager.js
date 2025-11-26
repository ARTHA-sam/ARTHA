const fs = require('fs-extra');
const path = require('path');
const axios = require('axios');
const ora = require('ora');
const chalk = require('chalk');

const DEPENDENCY_MAP = {
    'postgresql': 'org.postgresql:postgresql',
    'mysql': 'com.mysql:mysql-connector-j',
    'redis': 'redis.clients:jedis',
    'lombok': 'org.projectlombok:lombok',
    'gson': 'com.google.code.gson:gson',
    'jwt': 'com.auth0:java-jwt',
    'mongodb': 'org.mongodb:mongodb-driver-sync',
    'sqlite': 'org.xerial:sqlite-jdbc',
    'jackson': 'com.fasterxml.jackson.core:jackson-databind'
};

class DependencyManager {
    constructor(projectPath) {
        this.projectPath = projectPath;
        this.libDir = path.join(projectPath, '.artha', 'lib');
    }

    async install() {
        const configPath = path.join(this.projectPath, 'artha.json');
        if (!await fs.pathExists(configPath)) return [];
        const config = await fs.readJson(configPath);
        const dependencies = config.dependencies || {};

        if (Object.keys(dependencies).length === 0) return [];
        await fs.ensureDir(this.libDir);
        const jarPaths = [];

        for (const [name, version] of Object.entries(dependencies)) {
            const spinner = ora(`Installing ${name}@${version}`).start();
            try {
                const jarPath = await this.downloadDependency(name, version);
                jarPaths.push(jarPath);
                spinner.succeed(`${name}@${version}`);
            } catch (err) {
                spinner.fail(`${name}@${version}`);
                console.log(chalk.red("  " + err.message));
            }
        }
        return jarPaths;
    }

    async downloadDependency(name, version) {
        const mavenCoords = DEPENDENCY_MAP[name];
        if (!mavenCoords) throw new Error(`Unknown dependency: ${name}`);
        const [groupId, artifactId] = mavenCoords.split(':');
        const groupPath = groupId.replace(/\./g, '/');
        const url = `https://repo1.maven.org/maven2/${groupPath}/${artifactId}/${version}/${artifactId}-${version}.jar`;
        const jarName = `${artifactId}-${version}.jar`;
        const jarPath = path.join(this.libDir, jarName);

        if (await fs.pathExists(jarPath)) return jarPath;

        const response = await axios({
            method: 'GET',
            url: url,
            responseType: 'stream',
            timeout: 30000
        });

        const writer = fs.createWriteStream(jarPath);
        response.data.pipe(writer);
        return new Promise((res, rej) => {
            writer.on('finish', () => res(jarPath));
            writer.on('error', rej);
        });
    }

    async getClasspath() {
        const jarFiles = await fs.readdir(this.libDir).catch(() => []);
        return jarFiles.filter(f => f.endsWith('.jar')).map(f => path.join(this.libDir, f));
    }
}

module.exports = DependencyManager;
