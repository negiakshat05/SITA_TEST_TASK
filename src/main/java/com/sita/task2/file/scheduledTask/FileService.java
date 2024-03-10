package com.sita.task2.file.scheduledTask;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FileService {

	private static final Logger logger = LoggerFactory.getLogger(FileService.class);

	private Path inDirectry = Paths.get("C:\\SITA_TEST_TASK\\IN");
	private Path outDirectry = Paths.get("C:\\SITA_TEST_TASK\\OUT");
	private Path processedDirectry = Paths.get("C:\\SITA_TEST_TASK\\PROCESSED");
	private Path errorDirectry = Paths.get("C:\\SITA_TEST_TASK\\ERROR");

	@Scheduled(cron = "*/5 * * * * *")
	public void processFiles() {

		logger.info("Start of processFiles.");
		try {
			int sum = 0;
			List<Path> fileList = Files.list(inDirectry).collect(Collectors.toList());
			for (Path file : fileList) {
				try {
					List<String> lineList = Files.readAllLines(file);
					sum = lineList.stream().filter(line -> !line.trim().isBlank()).mapToInt(Integer::parseInt).sum();
					String opFileName = file.getFileName().toString() + ".OUTPUT";
					Path opFile = outDirectry.resolve(opFileName);
					Files.write(opFile, String.valueOf(sum).getBytes());
					Files.move(file, processedDirectry.resolve(file.getFileName() + ".PROCESSED"));
				} catch (IOException | NumberFormatException e) {
					Files.move(file, errorDirectry.resolve(file.getFileName() + ".ERROR"));
				}
			}
		} catch (Exception e) {
			logger.error("Exception occured due to {}", e.getMessage());
			e.printStackTrace();
		}
		logger.info("End of processFiles.");

	}

}
