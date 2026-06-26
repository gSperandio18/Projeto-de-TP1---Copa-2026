# 🖥️ Guia de Telas do Sistema

Este documento apresenta a interface gráfica do sistema, dividida por módulos funcionais e níveis de acesso, acompanhada da descrição de cada tela.

---

## 🔐 Módulo Inicial & Autenticação

Seção destinada ao primeiro contato do usuário com o sistema, englobando o controle de acesso, recuperação de credenciais e criação de novas contas.

<br>

### Tela de Login Inicial

A Tela Inicial permite ao usuário acessar sua área de trabalho a partir de suas credenciais. Os dados inseridos são validados no banco de dados para liberar o acesso correspondente ao nível de permissão do usuário.

<img width="80%" alt="Tela de login inicial" src="https://github.com/user-attachments/assets/bf494c31-16c0-4e1c-b81a-74ff6eaf3269" />

<br>

---

### Fluxo de Recuperação de Senha ("Esqueci a senha")

Caso o usuário perca o acesso à sua conta, este fluxo permite a redefinição segura da senha em três etapas consecutivas.

<br>

#### Tela 1: Identificação do Usuário

<img width="80%" alt="Tela 1 de Esqueci a senha" src="https://github.com/user-attachments/assets/7335524a-071f-4d07-8132-5811bafe4272" />

<br>

#### Tela 2: Código de Verificação

<img width="80%" alt="Tela 2 de Esqueci a senha" src="https://github.com/user-attachments/assets/954e797f-5a60-4b21-828f-a99524cea22d" />

<br>

#### Tela 3: Cadastro de Nova Senha

<img width="80%" alt="Tela 3 de Esqueci a senha" src="https://github.com/user-attachments/assets/3dba362e-da13-4f56-b9ff-52bfba1411b9" />

<br>

> ⚠️ **Warning:** A nova senha deve conter pelo menos 8 caracteres, incluindo letras maiúsculas, números e caracteres especiais para garantir a conformidade com as diretrizes de segurança.

<br>

---

### Tela de Cadastrar-se

Interface voltada para a criação de novas contas no sistema para usuários gerais.

<img width="80%" alt="Tela de Cadastrar-se" src="https://github.com/user-attachments/assets/39fe0ca5-52dd-42ef-825d-947c67e12831" />

<br>

### Tela de Login Bem-Sucedido

Mensagem temporária de confirmação exibida logo após a validação correta das credenciais do usuário.

<img width="80%" alt="Tela de Login Bem-Sucedido" src="https://github.com/user-attachments/assets/a75ff69d-2ed9-499a-924f-0735701cd5e1" />

<br>

---

## 👑 Módulo Administrador

> 👤 **Desenvolvido por:** João Guilherme Possati (Seção de Usuários)

<br>

Área exclusiva para usuários com privilégios de Administrador. Permite a auditoria completa do sistema através de relatórios e o gerenciamento global de contas.

<br>

### Painel Principal do Administrador

Dashboard inicial exibido logo após o login de uma conta de nível administrativo.

<img width="80%" alt="Tela principal após Login com Conta de Administrador" src="https://github.com/user-attachments/assets/9e8d0818-cb7f-49c3-816a-1a54ff6af836" />

<br>

### Aba "Relatórios"

Espaço centralizador para análise de dados do sistema, gráficos de desempenho e métricas gerais.

<img width="80%" alt="Tela da aba Relatórios - Visão 1" src="https://github.com/user-attachments/assets/8e91d0b9-6d38-47ed-91eb-a72bb3e824ac" />

<br>

<img width="80%" alt="Tela da aba Relatórios - Visão 2" src="https://github.com/user-attachments/assets/e75062d4-e652-4931-b17d-bd11c4ce4ef2" />

<br>

<img width="80%" alt="Tela da aba Relatórios - Visão 3" src="https://github.com/user-attachments/assets/c46494ea-4cc2-4fed-b647-f34cfc89ce0b" />

<br>

---

### Aba "Gestão de Usuários"

Interface para o controle de todas as contas cadastradas na plataforma (CRUD de usuários).

<img width="80%" alt="Tela da aba Gestão de Usuários" src="https://github.com/user-attachments/assets/59e13e66-6e36-40ea-8e35-8fa54f6f5a47" />

<br>

* **Criar novo usuário:** Formulário para inserção de novos colaboradores ou perfis no banco de dados.
    
    <img width="80%" alt="Tela de Criar novo usuário" src="https://github.com/user-attachments/assets/412219a8-fc51-49ee-98b8-f8bd5c1adf58" />

<br>

* **Editar usuário:** Permite a alteração de dados cadastrais e níveis de acesso de contas existentes.
    
    <img width="80%" alt="Tela de Editar usuário" src="https://github.com/user-attachments/assets/7e8eb44b-4e41-4ec1-b466-c832035c2d5e" />

<br>

* **Excluir usuário & Confirmação:** Fluxo de remoção de contas do sistema.
    
    <img width="80%" alt="Tela de Excluir usuário" src="https://github.com/user-attachments/assets/446b7f97-a469-4d09-866d-a9143ea75a6e" />

    <br>

    <img width="80%" alt="Mensagem usuário excluído" src="https://github.com/user-attachments/assets/a946dae5-e8f1-40cc-9f2c-42496126fe13" />

<br>

---

## 🏃‍♂️ Módulo de Organizadores e Administradores

Seção destinada à gestão operacional dos eventos desportivos, gerenciamento de elencos, locais e tabelas de jogos.

<br>

### Painel Principal do Organizador

Dashboard focado nas atividades diárias e painéis de controle do organizador de eventos.

<img width="80%" alt="Tela principal após login com Conta de Organizador" src="https://github.com/user-attachments/assets/7d700e2f-e106-4f01-918f-9c07cc3998ec" />

<br>

---

### ⚽ Gestão de Seleções e Jogadores

> 👤 **Desenvolvido por:** Helio Dias

<br>

Módulo responsável pela administração das delegações, inscrições de times e manutenção do plantel de atletas.

<br>

#### Gerenciar Seleções

Painel de listagem de equipes inscritas na competição.

<img width="80%" alt="Tela de Gerenciar Seleções" src="https://github.com/user-attachments/assets/04612a1f-c70f-4aee-8905-bff0a5eefcf2" />

<br>

* **Cadastrar Seleção:**
    
    <img width="80%" alt="Tela de Cadastrar Seleções" src="https://github.com/user-attachments/assets/00ab56b9-7bb2-4d38-9b3b-1a5924804e21" />

<br>

* **Editar Seleção:**
    
    <img width="80%" alt="Tela de Editar Seleção" src="https://github.com/user-attachments/assets/76f371d5-5b25-4460-a213-3335736c7de3" />

<br>

#### Gerenciar Jogadores

Interface para controle de atletas vinculados a cada seleção.

<img width="80%" alt="Tela de Gerenciar Jogadores" src="https://github.com/user-attachments/assets/d6b255b9-60e2-4ad5-8e25-ab8ba046067e" />

<br>

* **Cadastrar Jogador:**
    
    <img width="80%" alt="Tela de Cadastrar Jogador" src="https://github.com/user-attachments/assets/e0b1f200-5d99-4282-8b90-ae81ea99cd69" />

<br>

* **Editar Jogador:**
    
    <img width="80%" alt="Tela de Editar Jogador" src="https://github.com/user-attachments/assets/22dc5ce6-44ab-4c13-b29a-c299938058cb" />

<br>

---

### 🏟️ Gestão de Estádios e Árbitros

> 👤 **Desenvolvido por:** Giovanna Sperandio

<br>

Módulo focado na infraestrutura e na equipe de arbitragem responsável pela condução justa das partidas.

<br>

#### Gerenciar Estádios

Tela para listagem, cadastro e verificação de disponibilidade dos estádios e praças esportivas.

<img width="80%" alt="Tela de Gerenciar Estádios" src="https://github.com/user-attachments/assets/7c567fab-6991-4e08-bb38-9a026096b259" />

<br>

#### Gerenciar Árbitros

Painel para controle do corpo de arbitragem associado ao campeonato.

<img width="80%" alt="Tela de Gerenciar Árbitros" src="https://github.com/user-attachments/assets/306a59bb-5879-4758-9f40-cdb76de9adcf" />

<br>

> 💡 **Tip:** Um árbitro deve primeiro ser criado por um Administrador antes que lhe seja atribuído nacionalidade e experiência nesta tela. Também não é possível excluir árbitros nesta tela, visto que isso deve ser realizado pela tela de Gerenciar Usuários.

<br>

* **Designar Árbitro para Nova Partida:** Tela de escala de profissionais para jogos agendados.
    
    <img width="80%" alt="Tela de Designar Árbitro para Nova Partida" src="https://github.com/user-attachments/assets/50056c07-8566-4814-ab5d-54f80ace062c" />

<br>

---

### 📅 Gestão de Partidas

> 👤 **Desenvolvido por:** Leonardo Aoki

<br>

Módulo focado no calendário, agendamento, busca e súmula de confrontos.

<br>

#### Cadastrar Nova Partida

Formulário para definição de data, horário, local e seleções que irão se enfrentar.

<img width="80%" alt="Tela de Cadastrar Nova Partida" src="https://github.com/user-attachments/assets/e1000a3a-f586-43f5-a35d-87add0df198f" />

<br>

> ⚠️ **Warning:** Uma partida só pode ser cadastrada com seleções diferentes se enfrentando, e não poderá haver outro jogo no mesmo horário e no mesmo estádio. O sistema também faz a checagem de partidas simultâneas para a mesma seleção, e impede o cadastro caso a condição seja verdadeira.

<br>

> ⚠️ **Warning:** Os árbitros designados para a partida não podem ter a mesma nacionalidade que uma das seleções jogadoras.

<br>

#### Buscar Partidas

Filtros de pesquisa por data, campeonato ou seleção para localizar confrontos específicos.

<img width="80%" alt="Tela de Buscar Partidas" src="https://github.com/user-attachments/assets/9777788b-4f58-46f4-9349-19762eb28784" />

<br>

#### Registrar Resultado de Partidas

Interface para digitação do placar e ocorrências do jogo.

<img width="80%" alt="Tela de Registrar Resultado de Partidas" src="https://github.com/user-attachments/assets/00d338f4-f668-4638-8650-796c8e356f7f" />

<br>

#### Tela de Finalização da Partida

Visualização final da súmula após o encerramento oficial do jogo.

<img width="80%" alt="Tela quando a Partida é finalizada" src="https://github.com/user-attachments/assets/148956f5-9b7c-4492-904f-176009b1d8f4" />

<br>

---

## 🏁 Módulo do Árbitro

Área dedicada com ferramentas simplificadas para a checagem rápida de partidas por parte da equipe de arbitragem de campo.

<br>

### Painel Inicial do Árbitro

Dashboard enxuto apresentando a lista de partidas escaladas para o profissional autenticado.

<img width="80%" alt="Tela Inicial com Login de Árbitro" src="https://github.com/user-attachments/assets/50ae6966-df59-48f8-a745-448bc9432c59" />

<br>

---

## 📝 Conclusão & Autores

Este guia consolida a arquitetura visual desenvolvida para a interface do sistema, assegurando fluxos de trabalho distintos e otimizados para cada perfil de usuário. O esforço conjunto da equipe garantiu a separação lógica de responsabilidades e a consistência técnica da plataforma.

<br>

### Equipe de Desenvolvimento

| Desenvolvedor | Módulo de Responsabilidade | Perfil GitHub |
| :--- | :--- | :--- |
| **João Guilherme Possati** | Gestão de Usuários (Módulo Administrador) | [@João Guilherme Possati](https://github.com/JPossati) |
| **Helio Dias** | Gestão de Seleções e Jogadores | [@Helio Dias](https://github.com/heliovo) |
| **Giovanna Sperandio** | Gestão de Estádios e Árbitros | [@Giovanna Sperandio](https://github.com/gSperandio18) |
| **Leonardo Aoki** | Gestão e Calendário de Partidas | [@Leonardo Aoki]((https://github.com/LeoAokii)) |

<br>
