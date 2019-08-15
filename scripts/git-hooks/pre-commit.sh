#!/bin/sh
echo "Running static analysis..."

OUTPUT="/tmp/analysis-result"
./gradlew detekt > ${OUTPUT}
EXIT_CODE=$?

if [[ ${EXIT_CODE} -ne 0 ]]; then
    cat ${OUTPUT}
    rm ${OUTPUT}
    echo "*********************************************"
    echo "            Static Analysis Failed 2          "
    echo "Please fix the above issues before committing"
    echo "*********************************************"
    exit ${EXIT_CODE}
else
    echo "*********************************************"
    echo "      Static analysis found no problems      "
    echo "*********************************************"
    rm ${OUTPUT}
fi
