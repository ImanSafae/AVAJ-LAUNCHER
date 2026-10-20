Set-Location -LiteralPath $PSScriptRoot -ErrorAction Stop

if (Test-Path -LiteralPath .\build) {
    Get-ChildItem -LiteralPath .\build -Recurse -Filter *.class -File |
        ForEach-Object {
            Remove-Item -LiteralPath $_.FullName -ErrorAction Stop
        }
}

javac -d build (Get-ChildItem -Path .\iman -Recurse -Filter *.java -File).FullName
exit $LASTEXITCODE