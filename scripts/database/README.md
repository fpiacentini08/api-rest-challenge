# Database

This folder contains a docker compose file to start a PSQL 15.2 database as a Docker container.
The container exposes port 8087.
A database called history is created by default.

## Prerequisites
- Docker running


## Running locally
To run the container locally, execute the following:
```
docker-compose --file docker-compose-psql.yml up
```

To stop the container, simply press CTRL + C.
```aidl
docker rm -f database_db_1
docker volume rm database_db
```

If you want to connect to the database locally execute the following and then type the test password `postgres`:
```aidl
psql -h localhost -p 8087 -U postgres -d history -W
```
## Example

```aidl
$ docker-compose --file docker-compose-psql.yml up
Starting database_db_1 ... done
Attaching to database_db_1
db_1  |
db_1  | PostgreSQL Database directory appears to contain a database; Skipping initialization
db_1  |
db_1  | 2023-03-13 19:22:47.443 UTC [1] LOG:  starting PostgreSQL 15.2 on x86_64-pc-linux-musl, compiled by gcc (Alpine 12.2.1_git20220924-r4) 12.2.1 20220924, 64-bit
db_1  | 2023-03-13 19:22:47.444 UTC [1] LOG:  listening on IPv4 address "0.0.0.0", port 5432
db_1  | 2023-03-13 19:22:47.444 UTC [1] LOG:  listening on IPv6 address "::", port 5432
db_1  | 2023-03-13 19:22:47.450 UTC [1] LOG:  listening on Unix socket "/var/run/postgresql/.s.PGSQL.5432"
db_1  | 2023-03-13 19:22:47.462 UTC [24] LOG:  database system was interrupted; last known up at 2023-03-13 19:18:18 UTC
db_1  | 2023-03-13 19:22:47.959 UTC [24] LOG:  database system was not properly shut down; automatic recovery in progress
db_1  | 2023-03-13 19:22:47.965 UTC [24] LOG:  redo starts at 0/1921148
db_1  | 2023-03-13 19:22:47.968 UTC [24] LOG:  invalid record length at 0/19446A0: wanted 24, got 0
db_1  | 2023-03-13 19:22:47.969 UTC [24] LOG:  redo done at 0/1944668 system usage: CPU: user: 0.00 s, system: 0.00 s, elapsed: 0.00 s
db_1  | 2023-03-13 19:22:47.977 UTC [22] LOG:  checkpoint starting: end-of-recovery immediate wait
db_1  | 2023-03-13 19:22:48.008 UTC [22] LOG:  checkpoint complete: wrote 28 buffers (0.2%); 0 WAL file(s) added, 0 removed, 0 recycled; write=0.008 s, sync=0.013 s, total=0.034 s; sync files=10, longest=0.006 s, average=0.002 s; distance=141 kB, estimate=141 kB
db_1  | 2023-03-13 19:22:48.021 UTC [1] LOG:  database system is ready to accept connections
```