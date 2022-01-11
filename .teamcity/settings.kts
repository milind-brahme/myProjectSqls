import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.SSHUpload
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.sshExec
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.sshUpload
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2021.2"

project {
    description = "First project ; Kotlin moved to Github"

    vcsRoot(HttpsGithubComMilindBrahmeQueryLightGitRefsHeadsMaster)

    buildType(BuildConf1)
    buildType(Build)
}

object Build : BuildType({
    name = "MavenBuild"

    vcs {
        root(HttpsGithubComMilindBrahmeQueryLightGitRefsHeadsMaster)
    }

    steps {
        maven {
            goals = "clean test package"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
        }
    }

    triggers {
        vcs {
        }
    }
})

object BuildConf1 : BuildType({
    name = "buildConf1"

    params {
        param("sshKey", """
            -----BEGIN RSA PRIVATE KEY-----
            MIIEpAIBAAKCAQEAxHNOQUIBpQc/O30fOa8lOxf4uprmBFGM4RVpgr88h34eGrvw
            O3pXOt02nNeZ9Us+HUX7OtTVZ9Eqj4peeYMK/fjl+rmeO4Wj/xvF4AH6kizc7sy7
            zZ3IK01YadkA8LMV4am++n3Vg0X+KYAjLylmfXni3zfuojCfKdBFsV+7+/8KYPlh
            ihRWlwHJNXNnKJCk6wQZmqXua565Cbj0pevIm3VY/sBlPzFJbP2KWIbxGgprlXnO
            fZjQjwdIRbd/jh3UKt3i4a2gODfYJ2rbMpxmMkzFpLCIH3cRHgygg4k0J+xYxCuT
            /shLqLGbUHOtZ1tRAiT5YlINDqe6QewtZcAY5wIDAQABAoIBAQCMww4shk5FYHb3
            ekUYgR4NRLTtiLYOHBz03vHWRI7rivupLkcJZzT/sahDI91mPL7KHO3SDtTG6cI0
            TfI54SLMLj8js0Lk5XJIBi93vhM8EDdSSJK81zSpimcymNIqpjeklYuJUwbdZBak
            x/LuBR0yBVcBmMM9pdc2D80QNketXKVDLqhhe8kXo3a56okkzSPtT6tGQmJpIJGF
            DPym9OLAcci96UMUfmtYDyxPSw3eteCbGsgl3+45l27KfXPJN8vgA23PPbS0OyaQ
            JW+bgKMfFRKA0clIacSYcFNUi8pq7hf+8vOj35QCkkcKR0podqlvMq9Rr7sf6mQT
            JCvsJaRRAoGBAODvPWaYNeA0GGPnKaLKi9COkZAac+c5x96ZjLJERc/0AJuMG4e/
            iv5bU8PXPvI/l9n5wqdxrIgHb2ZlaxICEd0S1LMc8bpBZVcycvbI0u9UBs4SySkl
            JN473MKLIxYAB6bRAxJnPMxdSrb8r9h9fWfCac+1SUjpxDuRx29IyaBPAoGBAN+U
            /AtBTutsdxtt+gbWGrgTcThX/fN8B5vJEZu0o9dhpVQsoSnl7z84WwaKv6VDeo8d
            84Fi5ZUylsM+hwqoULx2nm34/szZYRGXOoteIrmPC2+WMrM91qOWiqA/lxhH02ia
            sMaX501eSNm3Jo+ssGK7fIWBZ7TJ+b4lldGRan/pAoGAIfffFOQ9lpsnZ8bcAcqG
            rLsq/lEQWcXfCh20IqOuy0W05ciVy71AV0MhdjzGM70oyHEwVSsUfAJrb1I3hiOr
            4O/cESR42MDRdgaXV+9h82SEFpC9sin61FjD8NkkNklQ3n9H8HqIsvAPvWyRfacN
            ziKM6iIdbBZpXPmZ3ZNnZ38CgYEAxvbdKOLwaT7365f95TGJS4A5xCtB2fjpCHBx
            U//3wcaqivLAZPmy466yCmtUyqv3F/9OYDjzF8eSNKOjJw/L8TsaF8F6di+IW1Aa
            EEYwXrL2OVIPQkToimGAccTFIB1i+/aVE9XAR7B84gpDrZ2+xc4UrPYQpOkMb3Mh
            FQL0JGkCgYBY3qULn0xUkno0kjuUDEOujXrb6vh4X/5SNiFNPO7B8EXjUUyAB9Dg
            07DbiFdY5NaG8Ing422mKKcEmfzbEUjZsRTchx9qr/IzC9ssXCCiMO75Nm3aOV1f
            METkd29UWAozFcfJ8dokV/v4AsNqns3gQLl/zOQqj/DONrij7Ewe3Q==
            -----END RSA PRIVATE KEY-----
        """.trimIndent())
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        sshUpload {
            name = "uploadCode"
            transportProtocol = SSHUpload.TransportProtocol.SCP
            sourcePath = "*"
            targetUrl = "192.168.0.12:deploy"
            authMethod = uploadedKey {
                username = "milind"
                key = "hive1key"
            }
        }
        sshExec {
            name = "runSql"
            commands = """
                cd deploy
                ls -lrt
                ls -1 | xargs -I % echo \\i % | psql -d wiself -v ON_ERROR_STOP=1
            """.trimIndent()
            targetUrl = "192.168.0.12"
            authMethod = uploadedKey {
                username = "milind"
                key = "hive1key"
            }
        }
    }
})

object HttpsGithubComMilindBrahmeQueryLightGitRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/milind-brahme/query-light.git#refs/heads/master"
    url = "https://github.com/milind-brahme/query-light.git"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
})
