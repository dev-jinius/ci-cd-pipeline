# ci-cd-pipeline

 Git branch 전략을 기반으로 Github Actions를 사용한 CI 구축 프로젝트.

## Overview
### 1. 배경
- 이전의 프로젝트들에서 PR 없이 merge 된 develop branch에 많은 충돌과 롤백으로 힘든 경험으로 프로젝트에 맞는 GIt branch 전략의 필요성을 느꼈다. 
- 협업 시 Git branch의 Event 시 지속적인 코드의 통합의 자동화가 개발 이외에 Git 관리에 들어가는 많은 시간을 줄여준다.

### 2. 목적 
- 개인, 소규모 프로젝트 또는 프로젝트 초기에 서버 없이 CI를 하기 위함.
- 빌드, 배포 시 필요한 환경 변수를 보안에 안전하게 관리한다.
- 앱 빌드와 테스트 빌드 workflow를 분리한다.

### 3. 핵심 기능
- Git flow
- CI
- Springboot Profile

## Server Phase
- dev
- prod

## Git branch strategy
### Git-flow transform
- branches
  - main : prod env for production and current release
  - develop : dev env for next release
  - feature : support branch for feature development of next release
- Why?
  - feature 단위로 개발을 하고, develop branch를 feature integration branch로 둔 이유는 기능을 개발했지만 반영이 필요없다거나, 어떠한 이유로 롤백을 해야한다거나 기능 단위로 필요한 시점을 정확히 알 수 있다.
  - 현재 개발 환경 구성에 stage가 없기 때문에 QA를 진행하는 서버가 없으므로 release branch를 제외시켰다.
- process
  ```
    1. Repository 생성 후 main branch를 default branch로 한다.
    2. 새로운 출시(또는 다음 출시) 버전을 개발하는 브랜치로 develop branch를 main branch에서 분기한다.
    3. 기능 개발 시 feature branch를 develop branch로부터 분기해서 개발 및 테스트를 한다.
    4. feature 개발 후 remote에 push 후, develop branch에 merge하는 Pull Request를 생성한다.
    5. develop branch에서 PR에 대한 리뷰 승인 후 Pull Request를 merge를 한다.
    6. 해당 feature branch를 삭제한다.
    7. develop branch에서 개발이 완료되면 main branch에 merge 한다. (merge --no-ff)
    8. 태그를 추가한다. (ex) 1.0.0
    9. main branch와 1.0.0 태그를 remote에 push한다.
  ```