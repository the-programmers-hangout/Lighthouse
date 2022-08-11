<p align="center">
   <a href="https://discord.gg/programming">
   <img alt="Discord Banner" src="https://discordapp.com/api/guilds/244230771232079873/widget.png?style=banner3"/>
   </a>
   <br/>
   <a href="https://kotlinlang.org/">
   <img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-1.7.10-%23A97BFF.svg?logo=Kotlin">
   </a>
   <a href="https://github.com/DiscordKt/DiscordKt">
   <img alt="DiscordKt" src="https://img.shields.io/badge/DiscordKt-0.23.3-%23E15ABA.svg?logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAYAAACM/rhtAAAABmJLR0QA/wD/AP+gvaeTAAAEvElEQVRYw+2Yy2seVRiHn3Ob756kiWlrq7ZqDSJUsSqoK4srQXHjRnShf4ILl/4FrkVcKLgS1FW3FRR05b1IMVXbNG2SJvny5ct3nZkz5+IitpW2giYOFskLA7M4Z+Y57/m9P857YC92F+Lqy/T0M/F2Atvc/EIAyNs9g3uAuw3974lZMFc9jsJwLj+Dj+72ATxSO8KrB1/nHjOHd5q1rM3Hm+/zc/b9f1vFTV3npbuf49k7ThJ9gisM3ulrz5nBj3zSfY+u39hxFe8IUCA4efgRXjv2PA05iXeaiGHyiRa2L+l87SnsNmRaBE53T3F68CnuH2z7jgHvm5rljRPPMTdxz7VMVeemOPTyYSp3JgCMzlt+fWdA71zEO41zmqV0hY823mPBzpcHeKg1wYcvvEJCDe8V1GpMv3iM5qMzN42NEdpfjFn4YETakXinyQt4e/ktLtvz5Rj1s/cfpVWPmEpO88Emh988cUs4ACFg/8k6j707w+wT23NqVc+Tk0+X54ONisBUcnRiqT9/P157vPd/OT7GiFM5B16SJJWcpJJTM6o8m6lVBCaxSBmIaU5sVXGFxXuB1gYpr/48YnNLlmYgInISTDVDFoZ6mYB3tAw6sUjlQVyXbAyRwlqUUiAEaZoSQ7imcCEiSSXHK89krVoeoNEek1i8V3hxc01577F5QSTcIMhtDUrlSYwrD3CyLrcz6BUBzy3LPsuham7gu57BZjUpr0jqFYE2BdoUmIX5bS+5ack3fDKC+2ENnVhMJadqYnkZbA9ztCkIMiCWziJ7bewjTxFmD17nUX8CXB/gv7pAuDTCJBrvFZ1sUB5gp+9Z35LMTjm0CsjxKuarUxQH7iV/+ElivQFCIEYp6odzhF9WiU5jEo2QnvZajc1RiRo0JtIbw3CkmZkKTLX+yGb7POqzRYqDx1AuIi5dIRSCkGikDPQtLK9VGI8jifHlAQ5zi1YFISjWupLeSLN/2lE1AekDanWe4BVBK7xQZCGy1jV0uooQLcoERn5YogZHGd3UMtXQaKmwXnLpimKiEZmddmgp8TIQRaDT1bTbGlsEjLE4qehmlp4dlQfoY6CfFgxTz76mpllVSKkYZprhkqHViOAl3X5CnguEdhgiQ+9pDwNpIfEyLw/w4sYYj0NIxcYwMBgrZiY0iY5EL+mPFdFHoijQJuKjY3Mo2RpFolBECYuD1fJ8sJ85Pp/f4EpvjFKBAsdyz9IeWKJyaO1QxiGVo59nLG9ljIscpR1bdsB36xdJfVZmkXhSF/lmsc++dsrxuxrsq1VIi8jlTqBZUcQg6I8FtpAorcl9wfzGgKWtETEKBkVa4hZ3Uu6cSjg6U6U7Lvjy1x5376vw0KEGiTQMck8MEoREKMFCZ8C59SHOgZSSy70Ri71u+U3T/omEx4+2mKprQJBIwYOHGhyZrkFULG9Zzi6NGeWeGCVjG/hppcul7t+3mF01TVcnHp2t8tiRFonePuNJISAIfIQYBD4IfmuP+Gmlh/NhR13djvviCCy0M1a6OcfvavLAgfr2IUtEBLDSt3y32GeY766B33XjnrvItxcH/LaeMXewipaCC+2M1Z69va4+tsYFX18o9m639gD/d4B7sdv4Hc8YdmfiizvVAAAAAElFTkSuQmCC">
   </a>
   <a href="https://hub.docker.com/repository/docker/theprogrammershangout/lighthouse/tags?page=1">
   <img alt="Docker" src="https://img.shields.io/docker/cloud/build/theprogrammershangout/lighthouse.svg?label=Docker&logo=docker">
   </a>
</p>

A simple discord bot that allows members to alert staff.

## Required Permissions
- Read Messages
- Send Messages
- Embed Links
- Add Reactions

The bot will also either need the `Mention @everyone, @here, and All Roles` permission **OR** (suggested) a channel override for your configured alert channel. 

## Commands
Refer to [commands.md](commands.md) for a general list and explanation of all available commands. To learn about commands during runtime, use the `help` command!

## Getting Started ⚙️
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Requirements
- Docker
- Docker-Compose (compatible with docker-compose version 3.8)

Get the Docker version you need [here](https://hub.docker.com/search?q=docker&type=edition&offering=community)!

### Setup
Since this bot has a **docker-compose.yml** file and is hosted on [DockerHub](https://hub.docker.com/r/theprogrammershangout/lighthouse), all you need to start your own version of Lighthouse locally is to `clone` this repository and set up the **.env** file.

The **.env** file is used to configure the bot token and owner id, in the following format: 

```
BOT_TOKEN=<insert-bot-token>
BOT_OWNER=<insert-owner-id>
```

#### For **Linux** and **Mac** run
```console
$ cp .env.example .env
```

#### For **Windows** run
```powershell
> Copy-Item .env.example .env
```

Edit the **.env** file with your favourite editor, filling out the following properties:
- **BOT_TOKEN** (you can find the bot token under `https://discord.com/developers/applications/bot-id/bot` for an overview of all your bots visit https://discord.com/developers/applications)
- **BOT_OWNER** ([Where can I find my User/Server/Message ID?](https://support.discord.com/hc/en-us/articles/206346498-Where-can-I-find-my-User-Server-Message-ID-))

Run the bot via `docker-compose`
```console
$ docker-compose up --build --detach
```

## Versioning 🏷️
This project uses [Semantic Versioning](http://semver.org/) for versioning. For the versions available, see the [tags](https://github.com/the-programmers-hangout/Lighthouse/tags/) on this repository.

## Authors 👤
* **Moe Szyslak** - *Original Author* - [@Moe-Szyslak](https://github.com/Moe-Szyslak)

See also the list of [contributors](https://github.com/the-programmers-hangout/Lighthouse/graphs/contributors) who participated in this project.

## Contributing 🤝
Contributions, issues and feature requests are welcome! Feel free to check the [issues page](https://github.com/the-programmers-hangout/Lighthouse/issues).

## Setup development environment 🛠️
For development you don't necessarily need **Docker** and **Docker-Compose** but **Java**
- `clone` this repository
- Open the project in your favourite Java / Kotlin IDE (JetBrains [IntelliJ IDEA](https://www.jetbrains.com/idea/) is the recommended IDE for Kotlin projects)
- For running the bot within your IDE you need to add your bot token as `program argument` or `environment variable` (You might have to figure out how to do that in your IDE. **IntelliJ** let's you do that under `Run -> Edit Configurations...`)

## Show your support ⭐️
Give a ⭐️ if this project helped you!

## License 📝
Copyright © 2020 [Moe Szyslak](https://github.com/Moe-Szyslak) <br>
This project is [MIT](LICENSE) licensed.