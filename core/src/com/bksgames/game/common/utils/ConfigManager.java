package com.bksgames.game.common.utils;

import com.badlogic.gdx.Gdx;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager implements Configuration{

	private final Properties properties;

	public ConfigManager(String fileName) {
		properties = new Properties();
		try
			(InputStream input = Gdx.files.internal(fileName).read()){
			properties.load(input);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}
