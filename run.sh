mkdir -p bin/spacevisuals
javac -cp "lib/*" -d bin $(find src -name "*.java")
echo "Program Compiled Successfully"
java -cp "lib/*:bin" spacevisuals.Main "$@"
echo "Program Ended"

