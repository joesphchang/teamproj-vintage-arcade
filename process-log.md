# Vintage Arcade Finder API – Process Log

## Overview
This document provides a high-level timeline of our team’s development process, including key milestones, contributions, and improvements made throughout the project lifecycle.

---

## Phase 1: Project Setup & Planning
**Dates: Mar 29 – Mar 31, 2026**

### Key Contributions:
- Established **team charter** and defined collaboration guidelines
- Created **team resume** outlining roles and responsibilities
- Updated initial **README** with project concept and scope
- Set up project structure:
  - Created packages and directory structure
  - Initialized Maven project (`pom.xml`)
  - Added placeholder `GenericDao` and test directories

### Outcome:
A fully initialized project with clear team expectations and foundational structure.

---

## Phase 2: Core Development & Architecture
**Dates: Apr 4 – Apr 7, 2026**

### Key Contributions:
- Implemented core **entity classes** (Venue, Cabinet, etc.)
- Built initial versions of **controllers (servlets)**
- Integrated **Hibernate ORM** and configured database properties
- Developed **GenericDao** for reusable data access
- Refactored controllers to use DAO pattern
- Established **entity relationships** (including many-to-many)
- Created initial **DAO test classes**

### Enhancements:
- Refactored servlet methods for improved structure
- Added **validation methods** for Venue and Cabinet
- Implemented **error handling** via `ApiError` class
- Added logging for debugging and traceability

### Outcome:
Working backend structure with database integration, controllers, and validation logic.

---

## Phase 3: Testing & Refinement
**Dates: Apr 8, 2026**

### Key Contributions:
- Expanded **unit testing coverage**, especially DAO tests
- Added **edge case scenarios** in `VenueDaoTest`
- Continued adding **Javadoc documentation**
- Improved test reliability and depth

### Outcome:
More robust and reliable backend with stronger validation through testing.

---

## Phase 4: REST API Implementation
**Dates: Apr 9 – Apr 11, 2026**

### Key Contributions:
- Implemented **RESTful API endpoints** for:
  - Venues
  - Cabinets
- Configured project to work with **Jakarta EE** (Tomcat 10 upgrade)
- Updated `pom.xml` dependencies
- Removed duplicate and unused files
- Fixed build issues (e.g., **Surefire plugin version**)

### Improvements:
- Refactored endpoints for consistency
- Cleaned up redundant methods
- Ensured proper request/response handling

### Outcome:
Fully functional REST API with CRUD operations.

---

## Phase 5: Filtering, Search & Documentation
**Dates: Apr 12 – Apr 13, 2026**

### Key Contributions:
- Implemented **filtering and search functionality**:
  - Venue filtering
  - Cabinet search (manufacturer, condition, etc.)
- Tested API using **seed data**
- Completed **Swagger/OpenAPI documentation**
- Added **Javadoc comments** across REST and servlet classes

### Outcome:
Feature-complete API with developer-friendly documentation and search capabilities.

---

## Phase 6: Finalization & Presentation Prep
**Date: Apr 14, 2026**

### Key Contributions:
- Updated and refined **team resume**
- Finalized formatting and documentation
- Prepared for presentation delivery

### Outcome:
Polished project ready for demonstration and evaluation.

---

## Team Collaboration & Workflow

### Version Control:
- Used **GitHub** for source control and collaboration
- Regular commits to track incremental progress
- Performed **rebasing and merging** to maintain a clean history

### Team Practices:
- Divided work based on roles and features
- Maintained shared understanding of all components
- Iteratively improved code through refactoring and feedback

---

## Challenges Encountered
- Designing consistent and scalable **REST endpoints**
- Managing **entity relationships** and ORM mappings
- Ensuring **test coverage** across DAO and service layers
- Resolving **merge conflicts** and dependency issues

---

## Key Takeaways
- Importance of **planning and architecture design**
- Value of **modular, layered development**
- Benefits of **testing early and often**
- Real-world experience with **team collaboration and Git workflows**

---

## Summary
Over the course of development, our team successfully designed and implemented a scalable RESTful API for locating vintage arcade machines and venues. Through iterative development, testing, and collaboration, we delivered a functional and well-documented system aligned with real-world software engineering practices.