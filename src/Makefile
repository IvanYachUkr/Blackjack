#
# Intended to be used on Unix-like systems
#
CC = javac
SERV_CLSS_DIR = ../out/dev/server/
CLNT_CLSS_DIR = ../out/dev/client/
BIN_DIR = ../bin/
CFLAGS_S = -d $(SERV_CLSS_DIR)
CFLAGS_C = -d $(CLNT_CLSS_DIR)
SERVER_FILES = server/*.java server/game/*.java server/gameentities/*.java
CLIENT_FILES = client/*.java
SERVER_MAIN = ServerApp
CLIENT_MAIN = ClientApp
MANIFEST_DIR_S = ../out/dev/server/META-INF/
MANIFEST_DIR_C = ../out/dev/client/META-INF/
MANIFEST_FILE = MANIFEST.MF

serv: $(SERVER_FILES)
	$(CC) $(CFLAGS_S) $^

clnt: $(CLIENT_FILES)
	$(CC) $(CFLAGS_C) $^

serv_run:
	java -cp $(SERV_CLSS_DIR) $(SERVER_MAIN)

clnt_run:
	java -cp $(CLNT_CLSS_DIR) $(CLIENT_MAIN)

server.jar: $(SERV_CLSS_DIR) $(MANIFEST_DIR_S) $(BIN_DIR)
	printf "Manifest-Version: 1.0\nMain-Class: $(SERVER_MAIN)\n" > \
			$(MANIFEST_DIR_S)$(MANIFEST_FILE)
	jar cfm $@ $(MANIFEST_DIR_S)$(MANIFEST_FILE) -C $(SERV_CLSS_DIR) .
	mv $@ $(BIN_DIR)

client.jar: $(CLNT_CLSS_DIR) $(MANIFEST_DIR_C) $(BIN_DIR)
	printf "Manifest-Version: 1.0\nMain-Class: $(CLIENT_MAIN)\n" > \
			$(MANIFEST_DIR_C)$(MANIFEST_FILE)
	jar cfm $@ $(MANIFEST_DIR_C)$(MANIFEST_FILE) -C $(CLNT_CLSS_DIR) .
	mv $@ $(BIN_DIR)

clean:
	rm -rf $(SERV_CLSS_DIR) $(CLNT_CLSS_DIR)

# Create a directory if doesn't exist
%/:
	mkdir -p $@
