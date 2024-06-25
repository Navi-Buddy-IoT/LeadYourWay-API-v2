package com.lyw.api.app.assets.domain.commands;

public record PatchGpsCommand(double latitude, double longitude, Long gpsId) {
} 