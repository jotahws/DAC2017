#!/bin/bash

#Função para o layout principal para a apresentaçao dos dois projetos
Principal() {
  echo " "
  echo " "
  echo "                                   --------------------------------------------------"
  echo '                                   |                PROJETOS DE S.O.                |'
  echo '                                   | ALUNOS: Gabriel Rosseto, João Henrique,        |'
  echo '                                   | Karine Mustefaga, Larissa Lins, Matheus Fialho |'
  echo '                                   --------------------------------------------------'
  echo ' '
  echo "                                               O que você deseja realizar?"
  echo
  echo "                                               1. Procurar um arquivo"
  echo "                                               2. Realizar uma eleição"
  echo "                                               3. Sair"
  echo
  echo -n "                                            Qual a opcao desejada? "
  read opcao
  #Uso do comando case para a escolha dos projetos
  case $opcao in
    1) Processo1 ;;
    2) Processo2 ;;
    3) clear ; exit ;;
    *) clear ; Principal ;;
  esac
}

#Função do projeto de procura de arquivos
Processo1() {
  clear
  echo ' '
  echo ' '
  echo ' '
  echo ' '
  echo "Digite o nome do arquivo a ser procurado:"
  read arq #Onde o usuário irá digitar o nome do arquivo
  n=$(find ~ -name $arq | wc -l) #uso do comando Find, com Pipe para a contagem do numero de linhas
  #Se o número de linhas for 0 significa que o arquivo é inexistente
  if [ $n = 0 ]; then
  echo ' '
  echo "Arquivo inexistente."
  else
  echo ' '
  echo "Arquivo existente."
  echo "Existe(m) $n arquivo(s) com esse nome."
  fi
  #uso dos comandos if para o retorno ao inicio do programa 
  echo 'Deseja procurar outro arquivo? (s/n)'
  read end
  if [ $end = "s" ]; then
  clear 
  Processo1
  fi 
  #uso do comando case para voltar à função principal
  echo -n "Ir para o Menu Trabalhos (s/n)? "
  read var
  case $var in
    s) clear ; Principal ;;
    n) clear ; exit ;;
    *) echo "Opcao desconhecida." ; echo ; Principal ;;
  esac
}

#Função do projeto de eleições
Processo2() {
  #Parte feita pelo administrador da votação
  clear
  #atribuição 0 às variaveis de contador
  ca=0
  cb=0
  cc=0
  cw=0
  cn=0
  echo ' '
  #leitura do número dos candidatos
  echo "Digite o número dos candidatos:"
  read a
  read b
  read c
  echo ' '
  #leitura do nome dos candidatos
  echo "Digite o nome dos candidatos:"
  read A
  read B
  read C
  clear


  #parte feita pelo eleitor
  echo " "
  echo " "
  echo "____________________________________________________________________________________________"
  echo " "
  echo "Digite o número do seu candidato, ou '.' para branco, ou qualquer outro caractere para nulo:"
  read v
  while [ $v != "fim" ]; do #uso do comando while para inicio das votações, encerrado pela paravra fim
  case $v in #uso do comando case para a seleção das opções dos candidatos
  $a) echo "Voto para candidato(a) $A? (s/n)"
  read r
  if [ $r = "s" ]; then #uso do comando if para a confirmação do candidato
  ca=$(($ca+1)) #contador dos votos para os candidatos (se repeta para todas as opções)
  echo "Voto computado com sucesso!"
  clear
  fi;;
  $b) echo "Voto para candidato(a) $B? (s/n)"
  read r
  if [ $r = "s" ]; then
  cb=$(($cb+1))
  echo "Voto computado com sucesso!"
  clear
  fi;;
  $c) echo "Voto para candidato(a) $C? (s/n)"
  read r
  if [ $r = "s" ]; then
  cc=$(($cc+1))
  echo "Voto computado com sucesso!"
  clear
  fi;;
  .) echo "Voto em branco? (s/n)"
  read r
  if [ $r = "s" ]; then
  cw=$(($cw+1))
  echo "Voto computado com sucesso!"
  clear
  fi;;
  *) echo "Voto nulo? (s/n)"
  read r
  if [ $r = "s" ]; then
  cn=$(($cn+1))
  echo "Voto computado com sucesso!"
  clear
  fi;;
  esac
  echo ' '
  echo ' '
  echo '____________________________________________________________________________________________'
  echo ' '
  echo "Digite o número do seu candidato, ou '.' para branco, ou qualquer outro caractere para nulo:"
  read v
  done

  #Encerramento das votações
  clear
  #uso do echo para a apresentação das variáveis
  echo "Votação encerrada."
  echo "Candidato(a) $A - $ca voto(s)"
  echo "Candidato(a) $B - $cb voto(s)"
  echo "Candidato(a) $C - $cc voto(s)"
  echo "Brancos - $cw voto(s)"
  echo "Nulos - $cn voto(s)"
  #uso do comando case para volta à função principal
  echo -n "Ir para o Menu Trabalhos (s/n)? "
  read var
  case $var in
    s) clear ; Principal;;
    n) clear ; exit ;;
    *) echo "Opcao desconhecida." ; echo ; Principal ;;
  esac
}

clear

Principal

