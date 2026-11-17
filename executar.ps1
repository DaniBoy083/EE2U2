param(
    [string]$Arquivo = 'dados/numeros.txt'
)

$ErrorActionPreference = 'Stop'
$encodingAnterior = [Console]::OutputEncoding
$entradaAnterior = [Console]::InputEncoding
$OutputEncoding = New-Object System.Text.UTF8Encoding($false)
[Console]::OutputEncoding = $OutputEncoding
[Console]::InputEncoding = $OutputEncoding

Push-Location -LiteralPath $PSScriptRoot
try {
    Get-Command javac -ErrorAction Stop | Out-Null
    Get-Command java -ErrorAction Stop | Out-Null
    New-Item -ItemType Directory -Path 'bin' -Force | Out-Null
    $fontes = Get-ChildItem -LiteralPath 'src' -Recurse -Filter '*.java' -File |
        Select-Object -ExpandProperty FullName

    & javac -encoding UTF-8 --release 17 -d bin $fontes
    if ($LASTEXITCODE -ne 0) {
        exit $LASTEXITCODE
    }

    & java '-Dfile.encoding=UTF-8' '-Dstdout.encoding=UTF-8' '-Dstderr.encoding=UTF-8' -cp bin ed2.projeto.Main $Arquivo
    exit $LASTEXITCODE
} finally {
    Pop-Location
    [Console]::OutputEncoding = $encodingAnterior
    [Console]::InputEncoding = $entradaAnterior
}
