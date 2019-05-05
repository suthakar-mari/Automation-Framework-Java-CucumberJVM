call mvn clean
call mvn -Dbrowser=chrome -Drun.on.browserstack.grid.local=local test -Dtag1=@regression -Dtag2=smoke test