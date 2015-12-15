## [4.1.3] - 2015-10-26
### Added
- Class cast exception if trying to read different file than expected.

### Fixed
- Catch possible error in reading files.

## [4.1.2] - 2015-08-28
### Added
- Send full referrer if it contains an adjust parameter.

### Changed
- Deprecated HttpClient replaced by HttpURLConnection.

## [4.1.1] - 2015-07-30
### Fixed
- Check if state is valid to prevent exception.

## [4.1.0] - 2015-07-17
### Changed
- Documentation updated.

### Fixed
- Preventing access to invalid state.

## [4.0.9] - 2015-06-30
### Added
- Sociomantic partner id.
- Read response of click packages.

### Changed
- Revenue logs match value send.

## [4.0.8] - 2015-06-10
### Changed
- Hash functions are now accessible for plugins.

### Fixed
- Timer is not created every time it starts.
- Install referrer does not send the first session while app is in background.

## [4.0.7] - 2015-06-05
### Added
- New click label parameter in attribution.
- Inject optional parameters in criteo events.
- Add partner id optional parameter in Criteo.

## [4.0.6] - 2015-05-08
### Added
- Support for muti-process apps.

### Changed
- Criteo plugin update.

## [4.0.5] - 2015-04-30
### Changed
- Criteo plugin update.
- Google Play Services availability check changed.

## [4.0.4] - 2015-04-23
### Changed
- Prefixing sociomantic parameters to avoid ambiguities.

## [4.0.3] - 2015-04-20
### Added
- Sociomantic plugin.

### Changed
- Documentation updated with information about Google Play Services v7.

### Fixed
- Fixed Criteo product string parsing.

## [4.0.2] - 2015-03-25
### Changed
- String formatting is using US locale now.

## [4.0.1] - 2015-03-23
### Added
- Criteo plugin is updated with new events.

### Changed
- Improved serialization and migration.

## [4.0.0] - 2015-03-13
### Added
- Config object for SDK initialization and launching.
- Currency is now send together with revenue.
- Possibility to add partner parameters to event.
- Introducing SDK offline mode.
- Criteo plugin allows to track Criteo type of events.

### Changed
- Replaced Response Data delegate with Attribution Changed delegate.
