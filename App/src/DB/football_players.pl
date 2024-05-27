player_position('Messi', 'Forward').
player_position('Ronaldo', 'Forward').
player_position('Neymar', 'Forward').
player_position('Pogba', 'Midfilder').
player_position('Kante', 'Midfilder').
player_position('Henderson', 'Midfilder').
player_position('Van Dijk', 'Defender').
player_position('Sergio Ramos', 'Defender').
player_position('Chiellini', 'Defender').

players_at_position(Position, Players) :-
findall(Player, player_position(Player,Position), Players).

% Predicado para buscar todos os jogadores
todos_jogadores(Jogadores) :-
    findall(Jogador, player_position(Jogador, _), Jogadores).