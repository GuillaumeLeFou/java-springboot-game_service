package com.example.game_service.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PlayerServiceClient {

    @Value("${player.service.url}")
    private String playerServiceUrl;

    private final RestTemplate restTemplate;

    public PlayerServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void updatePlayer(Long playerId, int score, boolean victoire) {
        String url = String.format("%s/Player/updateScoreAndLevel", playerServiceUrl);
        PlayerUpdateRequest request = new PlayerUpdateRequest(playerId, score, victoire);
        restTemplate.postForObject(url, request, Void.class);
    }

    static class PlayerUpdateRequest {
        private Long playerId;
        private int score;
        private boolean victoire;

        public PlayerUpdateRequest(Long playerId, int score, boolean victoire) {
            this.playerId = playerId;
            this.score = score;
            this.victoire = victoire;
        }

        public Long getPlayerId() {
            return playerId;
        }

        public void setPlayerId(Long playerId) {
            this.playerId = playerId;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public boolean isVictoire() {
            return victoire;
        }

        public void setVictoire(boolean victoire) {
            this.victoire = victoire;
        }
    }
}
