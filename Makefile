gradle-build: ## Run the Gradle build, including test suite
	@echo "Running the Gradle build, including test suite"
	@gradle build

gradle-build-image: gradle-build ## Run the Docker image build
	@echo "Running the Docker image build"
	@gradle bootBuildImage

.PHONY: help

help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

.DEFAULT_GOAL := help