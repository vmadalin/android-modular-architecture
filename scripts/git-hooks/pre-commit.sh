#!/bin/sh
echo "Running static analysis..."

OUTPUT="/tmp/analysis-result"
./gradlew app:detekt app:ktlint app:lintDebug --daemon > ${OUTPUT}
EXIT_CODE=$?

if [[ ${EXIT_CODE} -ne 0 ]]; then
    cat ${OUTPUT}
    rm ${OUTPUT}
    echo "*********************************************"
    echo "            Static Analysis Failed           "
    echo "Please fix the above issues before committing"
    echo "*********************************************"
    exit ${EXIT_CODE}
else
    echo "*********************************************"
    echo "      Static analysis no problems found      "
    echo "*********************************************"
    rm ${OUTPUT}
fi
