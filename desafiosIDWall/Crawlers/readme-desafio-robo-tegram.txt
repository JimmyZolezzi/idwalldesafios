Passo 1
Criação do Robo no Telegram:
Para criar o boot do Telegram devemos seguir os seguintes passos:
1-adicionar o bootFather as contatos
2-criar o bot com o seguintes comandos:
	/newbot
	Digite um nome para o bot
	Digite um username para bot terminado com bot ex idwallbot
	Anote o token para a próxima configuração

Passo 2
Configurar o arquivo propriedades no seguinte diretorio:
	C:\desafioJimmy\configuracoes.properties
	1.1 Colocar as propriedades a seguir conforme descrito:
	Caminho do Web Driver do Selenium, em específico estamos usando o phantomjs.
	
		DRIVER_SELENIUM_PATH=C:\\desafios\\phantomjs.exe
	Token do bot do Telegram, gerado na criação do Bot:
		BOT_TELEGRAM_TOKEN=380335216:AAHz8DRcZ_yrFCwfoP8eEDtWBOR0z4_m4vY
	Mínimo de Upvotes do Reddit para a consulta:
		MIN_UPVOTES=5000
3-Rodar o robo telegram:
	java -jar desafio-crawlers-robo-telegram.jar
4-Roda a busca reddit
	java -jar run-busca-reddit.jar news;askreddit


	

