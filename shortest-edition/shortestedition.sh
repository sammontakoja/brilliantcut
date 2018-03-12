#!/usr/bin/env bash

book="alastalon_salissa.txt"
validator="alastalo_validator"

downloadStuffIfNecessary() {
  echo "Downloading alastalon salissa stuff from Wunderdog's GitHub account if necessary..."
  [ ! -f $book ] && wget -q "https://raw.githubusercontent.com/wunderdogsw/wunderpahkina-vol8/master/$book"
  [ ! -f $validator ] && wget -q "https://github.com/wunderdogsw/wunderpahkina-vol8/raw/master/validators/validator_linux.zip"
}

takeValidatorOutOfDownloadedZip() {
  if [ -f "validator_linux.zip" ]
    then
      unzip -q "validator_linux.zip"
      mv "target/x86_64-unknown-linux-musl/release/alastalo_validator" .
      chmod a+x "alastalo_validator"
      rm -rf target validator_linux.zip
    fi
}

echo "Welcome to Kotlin implementation of the shortest edition problem!"
downloadStuffIfNecessary
takeValidatorOutOfDownloadedZip

echo "*** $book ***"
./alastalo_validator "$book"

echo "*** Compile project ***"
mvn clean install > /dev/null

rm reduced_lines.txt 2> /dev/null

echo "*** Let LineReducer do it's job... ***"
time java -jar target/shortestedition.jar "$book"

echo "*** RESULT ***"
./alastalo_validator reduced_lines.txt
